package com.khanchych.friday;

import java.util.*;

/**
 * Source https://leetcode.com/problems/course-schedule/
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 */
public class CourseSchedulingFeasibility {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> constraintsGraph = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int[] constraint : prerequisites) {
            constraintsGraph.putIfAbsent(constraint[0], new ArrayList<>());
            constraintsGraph.get(constraint[0]).add(constraint[1]);
        }

        for (Integer chainStart : constraintsGraph.keySet()) {
            int[] visitedCourses = new int[numCourses];
            queue.clear();

            queue.addAll(constraintsGraph.get(chainStart));
            while (!queue.isEmpty()) {
                Integer course = queue.poll();
                if (chainStart.equals(course)) {
                    return false;
                }
                if (visitedCourses[course] > 0) {
                    continue;
                }
                if (constraintsGraph.containsKey(course)) {
                    queue.addAll(constraintsGraph.get(course));
                }
                visitedCourses[course] = 1;
            }

        }

        return true;
    }
}