package com.yuxia.sbdemo.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.jupiter.api.Test;

public class TableDemo {
    @Test
    public void test1() {
        Table table = HashBasedTable.create();
        table.put("r1", "c1", "11");
        table.put("r1", "c2", "12");
        table.put("r2", "c1", "21");
        table.put("r2", "c2", "22");

    }
}
