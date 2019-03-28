package com.pancalka.funstruct

import scala.language.higherKinds

trait Monad[F[_]] {
  def unit[A](a: A*): F[A]

  def flatMap[A, B](m: F[A], f: A => F[B]): F[B]

  def map[A, B](m: F[A], f: A => B): F[B] = flatMap(m, (a: A) => unit(f(a)))

  def filter[A](m: F[A], p: A => Boolean): F[A] =
    flatMap(m, (a: A) => if (p(a)) unit(a) else unit())
}

object Monad {
  val optionMonad: Monad[Option] = new Monad[Option] {
    override def unit[A](a: A*): Option[A] = a.headOption

    override def flatMap[A, B](m: Option[A], f: A => Option[B]): Option[B] = m flatMap f
  }

  val listMonad: Monad[List] = new Monad[List] {
    override def unit[A](a: A*): List[A] = List(a: _*)

    override def flatMap[A, B](m: List[A], f: A => List[B]): List[B] = m flatMap f
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    println(Monad.listMonad.filter(List(1 to 10: _*), (a: Int) => a % 3 == 0))
    println(Monad.listMonad.map(List(1 to 10: _*), (a: Int) => a * a))
  }
}