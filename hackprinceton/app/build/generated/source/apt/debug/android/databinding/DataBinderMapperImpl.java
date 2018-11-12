package android.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new quartifex.com.gameoflife.DataBinderMapperImpl());
  }
}
