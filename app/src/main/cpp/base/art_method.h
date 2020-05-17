namespace art {

    namespace mirror {


        class Object {
            uint32_t klass_;
            uint32_t monitor_;
        };

        class ArtMethod : public Object {
        public:

            uint32_t access_flags_;//保留
            uint32_t dex_code_item_offset_;
            uint32_t core_spillmask_;

            const void *entyt_point_from_compiled_code_;
            const uint32_t *mapping_table_;


            uint32_t method_dex_index_;
            uint32_t dex_method_index_;

            uint32_t method_index_;//保留   方法表的索引
            const void *native_method_;
            const uint16_t *vmap_table_;

            uint32_t dex_cache_resolved_methods_;
            //方法  快照
            uint32_t dex_cache_resolved_types_;
            //声明的方法所属的函数
            uint32_t declaring_class_;

        };

    }  // namespace mirror
}  // namespace art