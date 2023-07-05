//package com.hzh.corejava.concurrent.problem;
//
//import org.openjdk.jcstress.annotations.Actor;
//import org.openjdk.jcstress.annotations.Expect;
//import org.openjdk.jcstress.annotations.JCStressTest;
//import org.openjdk.jcstress.annotations.Outcome;
//import org.openjdk.jcstress.infra.results.I_Result;
//
//@JCStressTest
//@Outcome(id = {"1", "4"}, expect = Expect.ACCEPTABLE, desc = "ok")
//@Outcome(id = {"0"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "danger")
//public class OrderingProblem {
//    int num = 0;
//    boolean ready = false;
//
//    @Actor
//    public void actor1(I_Result r) {
//        if (ready) {
//            r.r1 = num + num;
//        } else {
//            r.r1 = 1;
//        }
//    }
//
//    @Actor
//    public void actor2(I_Result r) {
//        num = 2;
//        ready = true;
//    }
//}
