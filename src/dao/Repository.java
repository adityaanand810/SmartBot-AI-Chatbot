package dao;

import java.util.List;

// RUBRIC: Generics
// T ek generic type hai.
// Iska matlab Repository ko User, Message, Knowledge
// kisi bhi object ke saath use kiya ja sakta hai.

public interface Repository<T> {

    // Create
    void save(T object) throws Exception;

    // Read
    List<T> getAll() throws Exception;

    // Update
    void update(T object) throws Exception;

    // Delete
    void delete(int id) throws Exception;
}