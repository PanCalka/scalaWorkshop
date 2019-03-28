//package com.pancalka.funstruct
//
//import scala.annotation.tailrec
//
//trait Tree[+A] {
//  def insert[B >: A](b: B)(implicit ord: Ordering[B]): Tree[B] = this match {
//    case Node(l, a, r) =>
//      if (ord.lt(b, a)) Node(l insert b, a, r)
//      else if (ord.lt(a, b)) Node(l, a, r insert b)
//      else this
//    case Empty => Node(Empty, b, Empty)
//  }
//
//  def flatMap[B](f: A => Tree[B])(implicit ord: Ordering[B]): Tree[B] = ???
//
//  def map[B](f: A => B): Tree[B] = this flatMap (a => Tree(f(a)))
//}
//
//case class Node[+A](l: Tree[A], a: A, r: Tree[A]) extends Tree[A]
//
//case object Empty extends Tree[Nothing]
//
//object Tree {
//  def apply[A](as: A*)(implicit ord: Ordering[A]): Tree[A] = {
//    @tailrec
//    def loop(t: Tree[A], as: Seq[A]): Tree[A] =
//      if (as.isEmpty) t
//      else loop(t insert as.head, as.tail)
//
//    loop(Empty, as)
//  }
//}
//
//object Test1 {
//  def main(args: Array[String]): Unit = {
//    println(Tree(2, 1, -1, 0))
//  }
//}