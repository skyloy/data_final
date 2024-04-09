package org.example;
/**
 * Класс черно-красного дерева с его свойствами
 */
class Tree{
    /**{@root} - корень, начальный элемент дерева*/
    Node root;

    /** Класс узла с его свойствами*/
    class Node{
        /** {@value} - значение, хранящиеся в узле дерева*/
        int value;
        /** {@left} - левый дочерний элемент узла дерева*/
        Node left;
        /** {@right} - правый дочерний элемент узла дерева*/
        Node right;
        /** {color} - цвет узла дерева*/
        Color color;
    }
    /**Класс - цвет. Содержит цвета для покраски узлов*/
    enum Color{
        /** Черный цвет */
        BLACK,
        /** Красный */
        RED
    }
    /**
     * Публичная функция добавления значения, вызывающая основную приватную функию добавления
     * @param value - значение для добавления
     */
    public void insert(int value){
        if(root != null) {
            insert(root, value);
            root = balance(root);
        }else{
            root = new Node();
            root.value = value;
        }
        root.color = Color.BLACK;
    }

    /**
     * Основная функция добавления значения в дерево
     * @param node - узел
     * @param value - значение для добавления
     */
    private void insert(Node node, int value){
        if(node.value != value) {
            if(node.value < value){
                if(node.right == null){
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = Color.RED;
                }else {
                    insert(node.right, value);
                    node = balance(node.right);
                }
            }else {
                if (node.left == null){
                    node.left = new Node();
                    node.left.value = value;
                    node.left.color = Color.RED;
                } else{
                    insert(node.left, value);
                    node = balance(node.left);
                }
            }
        }
    }

    /**
     * Функция запуска основной приватной функции поиска и возвращения результата
     * @param value - значение, которое нужно найти в дереве
     * @return возвращает результат основного метода поиска значения в дереве
     */
    public Node find(int value){
        return find(root, value);
    }

    /**
     * Функция нахождения значения в дереве
     * @param node -  узел, с которого начнется поиск значения в дереве(чаще всего корневой узел)
     * @param value - значение, которое нужно найти в дереве
     * @return если нашли искомое значени - возвращает его. Если нет - возвращает null
     */
    private Node find(Node node, int value){
        if(node == null){
            return null;
        }
        if(node.value == value){
            return node;
        }
        if(node.value > value){
            return find(node.left, value);
        }else {
            return find(node.right,value);
        }
    }

    /**
     * Функция левого поворота дерева
     * @param node - узел с которого начнём поворот
     * @return возвращает новый узел после поворота
     */
    private Node leftRotate(Node node){
        Node cur = node.right;
        node.right = cur.left;
        cur.left = node;
        cur.color = node.color;
        node.color = Color.RED;
        return cur;
    }

    /**
     * Функция правого поворота дерева
     * @param node - узел с которого начнём поворот
     * @return возвращает новый узел после поворота
     */
    private Node rightRotate(Node node){
        Node cur = node.left;
        node.left = cur.right;
        cur.right = node;
        cur.color = node.color;
        node.color = Color.RED;
        return cur;
    }

    /**
     * Функция перекрашивания узла дерева
     * @param node - узел, который перекрашивает
     */
    private void swapColors(Node node){
        node.color = (node.color == Color.RED ? Color.BLACK : Color.RED);
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    /**
     * Метод балансировки дерева по критериям задания
     * @param node - узел с которого начинается балансировка
     * @return возвращает узел уже сбалансированного дерева
     */
    private Node balance(Node node){
        boolean flag = true;
        Node cur = node;
        do{
            flag = false;

            if(cur.right != null && cur.right.color == Color.RED && (cur.left == null || cur.left.color == Color.BLACK)){
                cur = leftRotate(cur);
                flag = true;
            }

            if(cur.left != null && cur.left.color == Color.RED && cur.left.left != null && cur.left.left.color == Color.RED){
                cur = rightRotate(cur);
                flag = true;
            }

            if(cur.left != null && cur.left.color == Color.RED && cur.right != null && cur.right.color == Color.RED){
                swapColors(cur);
                flag = true;
            }
        }while(flag);
        return cur;
    }
}


public class Main {
    /**
     * Метод, в котором проверяем задачу, запускает, тестируем
     */
    public static void main(String[] args) {

    }
}