package com.opensource.module;

import java.util.Scanner;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class MyTest {
    public static void main(String[] args) {
        List<List<Integer>> res =new ArrayList<>();
        dfs(new int[]{1,2,3},-1,new ArrayList<Integer>(),res);
        System.out.println(res.toString());

    }

    public static void dfs(int[] nums,int idx,List<Integer> path,List<List<Integer>> res){
        if(path.size() == nums.length) return;
        if(idx != -1) path.add(nums[idx]);
        if(path.size() == nums.length) res.add(new ArrayList<>(path));
        dfs(nums,0,path,res);
        dfs(nums,1,path,res);
        dfs(nums,2,path,res);
        if(idx != -1) path.remove(path.size()-1);
    }
}