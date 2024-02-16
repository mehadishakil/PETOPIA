package com.example.petopia.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {

    @Insert
    void insertRecord(Item item);

    @Query("SELECT EXISTS (SELECT * FROM Item WHERE title =:itemTitle)")
    Boolean is_exist(String itemTitle);

    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Query("DELETE FROM Item WHERE id=:iID")
    void deleteById(int iID);



}
