var main = ({

    data() {
        return {
            list: [], // 列表
            total: 0, // 总记录数
            current: 1, // 页码
            limit: 10, // 每页记录数
            pages: '0' //总页码数
        }
    },
    created() {
        const that = this; //重写this指针
        axios.defaults.baseURL = "http://localhost:7000"; //定义全局的axios路径,后续开发中可修改一次改变所有
        that.fetchPageData(that.current, that.limit);
    },
    methods: {
        handleSizeChange: function (val) {
            const that = this; //rewriter this pointer
            that.limit = val;
            that.fetchPageData(that.current, that.limit);
        },
        handleCurrentChange: function (val) {
            const that = this; //rewriter this pointer
            that.current = val;
            that.fetchPageData(that.current, that.limit);
        },
        fetchPageData: function (current, limit) {
            const that = this;
            axios({
                    method: "get",
                    url: "/cart/api/list/carts",
                    params: {
                        currentPage: that.current,
                        pageSize: that.limit
                    }
                }).then(res => {
                    //console.log(res.data); //测试使用
                    if (res.status == 200) {
                        that.list = res.data.data;
                        that.total = res.data.total;
                        that.pages = res.data.pages;
                    } else {
                        that.list = res.data.data;
                        that.total = res.data.total;
                        that.pages = res.data.pages;
                        that.$notify({
                            tile: '警告',
                            message: '获取分页服务内部错误',
                            type: 'warning'
                        })
                    }
                })
                .catch(err => {
                    that.$notify.error({
                        title: '错误',
                        message: '获取分页数据失败',
                    })
                })
        }
    },
    mounted() {},
})
var Cart = Vue.extend(main)
new Cart().$mount('#app')