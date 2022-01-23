<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-form :layout="formState.layout" :model="formState" v-bind="formItemLayout">
        <a-form-item>
          <a-input v-model:value="formState.fieldA"/>
        </a-form-item>
        <a-form-item :wrapper-col="buttonItemLayout.wrapperCol">
          <a-button type="primary" @click="queryEbook">
            查询
          </a-button>
        </a-form-item>
        <a-form-item :wrapper-col="buttonItemLayout.wrapperCol">
          <a-button type="primary" @click="add">
            新增
          </a-button>
        </a-form-item>
      </a-form>

      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="ebooks"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:category="{text, record}">
          <span>{{ getCategoryName(record.category1Id) }} / {{ getCategoryName(record.category2Id) }}</span>
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <router-link to="/admin/doc">
              <a-button type="primary">
                文档管理
              </a-button>
            </router-link>
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-popconfirm
                title="删除无法回退，确认删除？"
                ok-text="是"
                cancel-text="否"
                @confirm="del(record.id)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
      title="电子书表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="ebook" :label-col="{span: 6}">
      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="分类">
        <a-cascader v-model:value="categoryIds"
                    :options="level1"
                    :field-names="{label: 'name', value: 'id', children: 'children'}"
                    placeholder="Please select" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.description" type="text"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, computed, UnwrapRef, reactive } from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import {Tool} from '@/util/tool';

export default defineComponent({
  name: 'AdminEbook',
  setup() {
    const ebooks = ref();
    const pagination = ref({
      current: 1,
      pageSize: 4,
      total: 0
    });
    const loading = ref(false);

    const columns = [
      {
        title: '封面',
        dataIndex: 'cover',
        slots: { customRender: 'cover' }
      },
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '分类',
        slots: {customRender: 'category'}
      },
      {
        title: '文档数',
        dataIndex: 'docCount'
      },
      {
        title: '阅读数',
        dataIndex: 'viewCount'
      },
      {
        title: '点赞数',
        dataIndex: 'voteCount'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      axios.get("/ebook/list", {
        params: {
          page: params.page,
          size: params.size,
          name: params.name
        }
      }).then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.success) {
          ebooks.value = data.data.data;

          // 重置分页按钮
          pagination.value.current = params.page;
          pagination.value.total = data.data.total;
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    /**
     * 表单
     */
    // 数组['100', '101']对应：前端开发/Vue
    const categoryIds = ref();
    const ebook = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      ebook.value.category1Id = categoryIds.value[0];
      ebook.value.category2Id = categoryIds.value[1];
      axios.post("/ebook/save", ebook.value).then((response) => {
        modalLoading.value = false;
        if (response.data.success) {
          modalVisible.value = false;

          // 重新加载数据
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          });
        } else {
          message.error(response.data.message);
        }
      })
    };

    /**
     * 编辑
     * @param record 待编辑记录
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      ebook.value = Tool.copy(record);
      categoryIds.value = [ebook.value.category1Id, ebook.value.category2Id]
    }

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      ebook.value = {};
    }

    /**
     * 删除
     */
    const del = (id: any) => {
      axios.delete("/ebook/delete/" + id).then((response) => {
        if (response.data.success) {
          // 重新加载数据
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          });
        }
      })
    };

    /**
     * 查询
     */
    const queryEbook = () => {
      handleQuery({
        page: 1,
        size: pagination.value.pageSize,
        name: formState.fieldA
      });
    }

    /**
     * 分类查询
     **/
    let categorys: any;
    const level1 = ref();
    const handleQueryCategory = () => {
      loading.value = true;
      axios.get("/category/all").then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.success) {
          categorys = data.data;
          level1.value = [];
          level1.value = Tool.array2Tree(data.data, '000');
          // 加载完分类后再加载电子书，防止分类加载慢导致的数据渲染异常
          handleQuery({
            page: 1,
            size: pagination.value.pageSize
          });
        } else {
          message.error(data.message);
        }
      });
    };

    const getCategoryName = (cid: any) => {
      let result = '';
      categorys.forEach((item: any) => {
        if (item.id === cid) {
          result = item.name;
        }
      });
      return result;
    }


    interface FormState {
      layout: 'horizontal' | 'vertical' | 'inline';
      fieldA: string;
    }

    const formState: UnwrapRef<FormState> = reactive({
      layout: 'inline',
      fieldA: ''
    });

    const buttonItemLayout = computed(() => {
      return {};
    });

    const formItemLayout = computed(() => {
      return {};
    });

    onMounted(() => {
      handleQueryCategory();
    });

    return {
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChange,
      handleModalOk,
      edit,
      modalVisible,
      modalLoading,
      ebook,
      add,
      del,
      buttonItemLayout,
      formState,
      formItemLayout,
      queryEbook,
      categoryIds,
      level1,
      getCategoryName
    }
  }
});
</script>