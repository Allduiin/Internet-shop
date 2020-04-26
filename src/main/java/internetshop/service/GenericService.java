package internetshop.service;

import java.util.List;

public interface GenericService<T> {
    T getById(Long id);

    List<T> getAll();

    boolean delete(Long id);
}
