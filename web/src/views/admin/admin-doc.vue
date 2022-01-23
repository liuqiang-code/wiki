<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-form :layout="formState.layout" :model="formState" v-bind="formItemLayout">
        <a-form-item :wrapper-col="buttonItemLayout.wrapperCol">
          <a-button type="primary" @click="queryDoc">
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
          :data-source="level1"
          :loading="loading"
          :pagination="false"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
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
      title="文档表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="doc" :label-col="{span: 6}">
      <a-form-item label="名称">
        <a-input v-model:value="doc.name" />
      </a-form-item>
      <a-form-item label="父文档">
        <a-select
            ref="select"
            v-model:value="doc.parent"
        >
          <a-select-option value="000">
            无
          </a-select-option>
          <a-select-option v-for="c in level1" :key="c.id" :value="c.id" :disabled="doc.id === c.id">
            {{c.name}}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="顺序">
        <a-input v-model:value="doc.sort" />
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
  name: 'AdminDoc',
  setup() {
    const loading = ref(false);

    const columns = [
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '父文档',
        key: 'parent',
        dataIndex: 'parent',
      },
      {
        title: '顺序',
        dataIndex: 'sort'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * 一级文档树，children 属性就是二级文档
     * [{
     *    id: '',
     *    name: '',
     *    children: [{
     *      id: ''，
     *      name: ''
     *    }]
     * }]
     */
    const level1 = ref();

    /**
     * 数据查询
     **/
    const handleQuery = () => {
      loading.value = true;
      axios.get("/doc/all").then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.success) {
          level1.value = [];
          level1.value = Tool.array2Tree(data.data, 0);
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 表单
     */
    const doc = ref({})
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      axios.post("/doc/save", doc.value).then((response) => {
        modalLoading.value = false;
        if (response.data.success) {
          modalVisible.value = false;

          // 重新加载数据
          handleQuery();
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
      doc.value = Tool.copy(record);
    }

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      doc.value = {};
    }

    /**
     * 删除
     */
    const del = (id: any) => {
      axios.delete("/doc/delete/" + id).then((response) => {
        if (response.data.success) {
          // 重新加载数据
          handleQuery();
        }
      })
    };

    /**
     * 查询
     */
    const queryDoc = () => {
      handleQuery();
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
      handleQuery();
    });

    return {
      columns,
      loading,
      handleModalOk,
      edit,
      modalVisible,
      modalLoading,
      doc,
      add,
      del,
      buttonItemLayout,
      formState,
      formItemLayout,
      queryDoc,
      level1
    }
  }
});
</script>