package com.jiedaibao.sbdemo.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("PcacList")
public class PcacList {
    int count=2;
    @XStreamImplicit
    List<C> riskinfo=new ArrayList<>();
    {
        riskinfo.add(new C());
        riskinfo.add(new C());
    }
}
