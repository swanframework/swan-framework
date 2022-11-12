```java
public class BaseMapper<ID,T>{

    boolean insert(T t);
    boolean insertNotNull(T t);
    int insertList(List<T> t);

    boolean delete(T t);  // id & version
    boolean deleteById(T t); // id
    int deleteListInIds(Collections<ID> ids);
    int deleteOn(C c);
    
    boolean update(T t);            // id & version
    boolean updateById(T t);        // id
    boolean updateNotNull(T t);     //id & version
    boolean updateNotNullById(T t); //id
    boolean updateOn(U u, C c);     // 自定义条件
    
    boolean UpdateFields(List list, T t);       // id&version
    boolean UpdateFieldsById(List list, T t);   // id
    boolean updateFieldsOn(List list, U u, C c);    // 条件
    
    T selectById(ID id);
    List<T> selectListInIds(Collection<ID> ids);
    List<T> selectListOn(C c);
    List<T> selectSortListOn(C c, R... r);
    
    T selectFieldsById(List list, ID id);
    List<T> selectFieldsListInIds(List list, Collection<ID> ids);
    List<T> selectFieldsSortListOn(List list,C c, R... r);

    int count();
    int count(C c);
    
    boolean containsById(ID id);
    boolean containsOn(C c);
    

}



```