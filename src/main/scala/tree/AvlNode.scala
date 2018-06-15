package tree

import java.util

/**
  * Created by zhaolei on 2018/6/13
  */
class AvlNode[K, V](var key: K, var value: V, var left: AvlNode[K, V], var right: AvlNode[K, V]) {
  var attach: Set[V] = Set.empty[V]
  var height: Int    = 0
  attach += value

}

object RotateAvlNode {

  /**
    * 左子树左边节点
    */
  def rotateLL[K, V](node: AvlNode[K, V]): AvlNode[K, V] = {
    //    https://segmentfault.com/img/remote/1460000006123262
    val top = node.left
    node.left = top.right
    top.right = node
    // 计算节点高度
    node.height = height(node.left).max(height(node.right)) + 1
    top.height  = height(top.left).max(height(top.right)) + 1
    top
  }

  /**
    * 右子树右边节点
    */
  def rotateRR[K, V](node: AvlNode[K, V]): AvlNode[K, V] = {
    //    https://segmentfault.com/img/remote/1460000006123258
    val top = node.right
    node.right = top.left
    top.left   = node
    // 计算节点高度
    node.height = height(node.left).max(height(node.right)) + 1
    top.height  = height(top.left).max(height(top.right)) + 1
    top
  }

  /**
    * 左子树右边节点
    */
  def rotateLR[K, V](node: AvlNode[K, V]): AvlNode[K, V] = {
    //    https://segmentfault.com/img/remote/1460000006123272
    node.left = rotateRR(node.left)
    rotateLL(node)
  }

  /**
    * 右子树左节点
    */
  def rotateRL[K, V](node: AvlNode[K, V]): AvlNode[K, V] = {
    //    https://segmentfault.com/img/remote/1460000006123285
    node.right = rotateLL(node.right)
    rotateRR(node)
  }

  /**
    * 添加
    */

  def add[K <: Comparable[K], V](key: K, value: V, tree: AvlNode[K, V]): AvlNode[K, V] = {
    if (tree == null) return new AvlNode(key, value, null, null)
    // 左子树
    var retTree: AvlNode[K, V] = tree
    if (key.compareTo(retTree.key) < 0) {
      retTree.left = add(key, value, retTree.left)
      if (height(retTree.left) - height(retTree.right) == 2) {
        if (key.compareTo(retTree.left.key) < 0) retTree = rotateLL(retTree)
        else retTree                                     = rotateLR(retTree)
      }
    }
    // 右子树
    if (key.compareTo(retTree.key) > 0) {
      retTree.right = add(key, value, retTree.right)
      if (height(retTree.right) - height(retTree.left) == 2) {
        if (key.compareTo(retTree.right.key) > 0) retTree = rotateRR(retTree)
        else retTree                                      = rotateRL(retTree)
      }
    }

    // 相等
    if (key.compareTo(retTree.key) == 0) {
      retTree.attach += value
    }
    // 计算高度
    retTree.height = height(retTree.left).max(height(retTree.right)) + 1
    retTree
  }

  def delete[K <: Comparable[K], V](key: K, value: V, tree: AvlNode[K, V]): AvlNode[K, V] = {
    if (tree == null) return null
    // 点在左子树
    var retTree: AvlNode[K, V] = tree
    if (key.compareTo(retTree.key) < 0) {
      retTree.left = delete(key, value, retTree.left)
      if (height(retTree.right) - height(retTree.left) == 2) { // 右子树比左子树高
        if (height(retTree.right.left) - height(retTree.right.right) > 0) retTree = rotateRL(retTree) // 右子树的左子树高与右子树
        else retTree                                                              = rotateRR(retTree)
      }
    }
    // 点在右子树
    if (key.compareTo(retTree.key) > 0) {
      retTree.right = delete(key, value, retTree.right)
      if (height(retTree.left) - height(retTree.right) == 2) { // 左树高于右树
        if (height(retTree.left.left) - height(retTree.left.right) > 0) retTree = rotateLL(retTree) // 左树的左树 高于左树的右树
        else retTree                                                            = rotateLR(retTree)
      }

    }
    // 当前节点
    if (key.compareTo(retTree.key) == 0) {
      if (retTree.right != null) { // 右子树不为空
        var tmpRight = retTree.right
        while (tmpRight.left != null) tmpRight = tmpRight.left
        retTree.value  = tmpRight.value
        retTree.height = tmpRight.height
        retTree.right  = delete(retTree.key, tmpRight.value, retTree)
      } else {}
    }
    retTree.height = height(retTree.left).max(height(retTree.right)) + 1
    retTree

  }

  /**
    * 当前节点的高度
    */
  def height[K, V](node: AvlNode[K, V]): Int = if (node == null) -1 else node.height

}

object AvlNode extends App {
  private val ints = new util.ArrayList[String](10)
  ints.add(0, 0.toString)
  ints.add(1, 1.toString)
  ints.add(2, 2.toString)
  ints.add(3, 3.toString)
  ints.add(4, 4.toString)
  ints.add(5, 5.toString)
  ints.add(6, 6.toString)
  ints.add(7, 7.toString)
  ints.add(8, null)
  System.err.println(ints)
  System.err.println(ints.indexOf(null))

}
