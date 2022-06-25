package wj.genericrabbit.app.data.mapper

interface Mapper<in I, out O> : (I) -> O