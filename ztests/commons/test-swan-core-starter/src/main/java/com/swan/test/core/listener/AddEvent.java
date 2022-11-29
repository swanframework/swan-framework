package com.swan.test.core.listener;

import com.swan.core.listener.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zongf
 * @since 2022-11-29
 **/
@Data
@AllArgsConstructor
public class AddEvent extends Event {

    private Integer id;

    private String name;


}
