```java
public class BaseMapper<ID,T>{

    public int count();
    public int countOnCondition(Condition condition);

    public boolean insert(E e);
    public boolean insertNotNull(E e);
    public int insertList(List<E> list);

    public boolean delete(E e);
    public boolean deleteById(ID id);
    public int deleteByIds(List<ID> list);

    public boolean updateById(E e);
    public boolean updateNotNullById(E e);
    public boolean update(E e);             // 乐观锁有版本号
    public boolean updateNotNull(E e);      // 有版本号

    public E selectById(ID id);
    public E select(Condition condition, SelectOption... option);

    public List<E> selectListByIds(List<ID> ids, SelectOption... option);
    public List<E> selectList(Condition condition, SelectOption... option);

//    // 查询一个
//    public E selectById(ID id);
//    public List<E> selectListByIds(List<ID> ids, OrderBy...  );
//    public List<E> selectList(Condition condition, OrderBy...  );
//
//    // 查询字段
//    public E selectFieldsById(SelectFields fields, ID id);
//    public List<E> selectFieldsByIds(SelectFields fields, List<ID> ids, OrderBy...  );
//    public List<E> selectFields(SelectFields fields, Condition condition, OrderBy...  );

    /**

     public E selectById(ID id);
     public E selectOnCondition(Selector condition);
     public List<E> selectListByIds(List<ID> ids);
     public List<E> selectList(Selector selector);

     */
    

}



```