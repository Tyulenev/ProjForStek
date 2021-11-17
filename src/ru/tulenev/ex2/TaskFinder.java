package ru.tulenev.ex2;

import ru.tulenev.TestRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TaskFinder {

    public static class Node {
        Node(int id, String name, Integer priority, List<Node> children) {
            this.id = id;
            this.name = name;
            this.priority = priority;
            this.children = children;
        }

        boolean isGroup() {
            return children != null;
        }

        int id;
        String name;
        Integer priority;
        List<Node> children;

        //Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getPriority() {
            return priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return id == node.id
                    && name.equals(node.name)
                    && Objects.equals(priority, node.priority)
                    && Objects.equals(children, node.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, priority, children);
        }
    }

    public static Node task(int id, String name, int priority) {
        return new Node(id, name, priority, null);
    }

    static  Node group(int id, String name, Node... children) {
        return new Node(id, name, null, Arrays.asList(children));
    }


    public static Node tasks =
            group(0, "Все задачи",
                    group(1, "Разработка",
                            task(2, "Планирование разработок", 1),
                            task(3, "Подготовка релиза", 4),
                            task(4, "Оптимизация", 2)),
                    group(5, "Тестирование",
                            group(6, "Ручное тестирование",
                                    task(7, "Составление тест-планов", 3),
                                    task(8, "Выполнение тестов", 6)),
                            group(9, "Автоматическое тестирование",
                                    task(10, "Составление тест-планов", 3),
                                    task(11, "Написание тестов", 3))),
                    group(12, "Аналитика"));


//    static Node maxPriorityNode = task(-11, "-11", -11);
    static boolean setRes = false;
    static boolean groupFinded = false;
    static Optional<Node> returnVal = Optional.empty();
    static Node maxPriorityNode = null;
    public static Optional<Node> findTaskHavingMaxPriorityInGroup(Node tasks, int groupId) throws Exception {
        // ------------------------------------------------------------------------------------------------
        // Решение задачи 2
        // ------------------------------------------------------------------------------------------------
//        System.out.println("Call meth with param: " +
//                "\ntasks.id - " + tasks.id +
//                "\ntasks.priority - " + tasks.priority
////                + "\ntasks.children.size() - " + tasks.children.size() //children м.б. не быть
//                + "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
//                );
        final int groupIdIntro = -999;


        if (tasks.isGroup()) {
            if (tasks.id == groupId) {
                groupFinded = true;
                if (tasks.children.size()>0) {
                    for (Node n:tasks.children) {
                        findTaskHavingMaxPriorityInGroup(n, groupIdIntro);
                    }
                    setRes = true;
                }

            } else {
                for (Node n:tasks.children) {
                    findTaskHavingMaxPriorityInGroup(n, groupId);
                }
            }
            setRes = true;
        } else {

            if (groupId==groupIdIntro) {
                if (maxPriorityNode != null) {
                    if (maxPriorityNode.priority < tasks.priority)  {
                        maxPriorityNode = tasks;
                    }
                } else {
                    maxPriorityNode = tasks;
                }
            }
        }


//        if (setRes) {
//            if (maxPriorityNode == null)
////            //      && (groupId == groupIdIntro) || (tasks.id == groupId)
//            {
//                throw new Exception("Нет группы с таким номером!!!!");
////            } else if (maxPriorityNode.id == -1) {
////                returnVal = null;
//            } else Optional.ofNullable(maxPriorityNode);
////            return Optional.ofNullable(maxPriorityNode);
//        }
        if ((!groupFinded) && (setRes)) {
            throw new Exception("Нет группы с таким номером!!!!");
        }



//        System.out.println("End Call meth with param: " +
//                        "\ntasks.id - " + tasks.id +
//                        "\ntasks.priority - " + tasks.priority
////                + "\ntasks.children.size() - " + tasks.children.size() //children м.б. не быть
//                        + "\n----------------------------------------------------------------------"
//        );

        return Optional.ofNullable(maxPriorityNode);

    }



    public static void testFindTaskHavingMaxPriorityInGroup() {
        TestRunner runner = new TestRunner("findTaskHavingMaxPriorityInGroup");

        runner.expectException(() -> findTaskHavingMaxPriorityInGroup(tasks, 13));
        runner.expectException(() -> findTaskHavingMaxPriorityInGroup(tasks, 2));

        runner.expectFalse(() -> findTaskHavingMaxPriorityInGroup(tasks, 12).isPresent());

        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 0).get()
                .equals(task(8, "Выполнение тестов", 6)));
        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 1).get()
                .equals(task(3, "Подготовка релиза", 4)));

        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 9).get().priority == 3);
    }
}
