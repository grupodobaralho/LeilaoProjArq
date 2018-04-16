package model;

import java.util.ArrayList;

public interface DAO {
	  
    public abstract boolean insert(Object objeto);
    public abstract ArrayList<Object> selectAll();
    public abstract Object selectEspecificData();

}
