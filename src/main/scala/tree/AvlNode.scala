package tree

import java.util
import java.util.Collections

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
    * 当前节点的高度
    */
  def height[K, V](node: AvlNode[K, V]): Int = ???

}

object AvlNode extends App {
  private val list: util.List[Int] = Collections.emptyList()
  list.add(100)
  System.err.println(list)
}
