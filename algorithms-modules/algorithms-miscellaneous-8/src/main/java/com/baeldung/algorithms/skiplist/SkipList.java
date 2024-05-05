package com.baeldung.algorithms.skiplist;


import java.util.Random;

public class SkipList {
    private Node head;
    private int maxLevel;
    private int level;
    private Random random;

    public SkipList() {
        maxLevel = 16; // maximum number of levels
        level = 0; // current level of SkipList
        head = new Node(Integer.MIN_VALUE, maxLevel);
        random = new Random();
    }

    public void insert(int value) {
        var update = new Node[maxLevel + 1];
        var current = this.head;

        for (var i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];

        if (current == null || current.value != value) {
            var lvl = randomLevel();

            if (lvl > level) {
                for (var i = level + 1; i <= lvl; i++) {
                    update[i] = head;
                }
                level = lvl;
            }

            var newNode = new Node(value, lvl);
            for (var i = 0; i <= lvl; i++) {
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
        }
    }

    public boolean search(int value) {
        var current = this.head;
        for (var i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        return current != null && current.value == value;
    }

    public void delete(int value) {
        var update = new Node[maxLevel + 1];
        var current = this.head;

        for (var i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }
        current = current.forward[0];

        if (current != null && current.value == value) {
            for (var i = 0; i <= level; i++) {
                if (update[i].forward[i] != current) {
                    break;
                }
                update[i].forward[i] = current.forward[i];
            }

            while (level > 0 && head.forward[level] == null) {
                level--;
            }
        }
    }

    private int randomLevel() {
        var lvl = 0;
        while (lvl < maxLevel && random.nextDouble() < 0.5) {
            lvl++;
        }
        return lvl;
    }
}
