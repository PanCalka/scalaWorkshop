package com.pancalka.funstruct

trait List[+A] {
  def flatMap[B](f: A => List[B]): List[B] = this match {
    case Nil => Nil
    case Cons(h, tail) => f(h) ++ tail.flatMap(f)
  }

  def ++[B >: A](l: List[B]): List[B] = this match {
    case Nil => l
    case Cons(h, t) => Cons(h, t ++ l)
  }
}

case class Cons[+A](head: A, tail: List[A]) extends List[A]

case object Nil extends List[Nothing]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}

object Main {
  def main(args: Array[String]): Unit = {

  }
}
