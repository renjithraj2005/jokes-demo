package com.jokes.demo.entity.core;

import java.io.Serializable;

public interface IEntity<ID extends Serializable> extends Serializable {

    ID getId();

    String friendlyName();

}
