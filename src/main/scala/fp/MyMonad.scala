package fp

trait MyFunctor[F[_]]:
    def map[A, B](in: F[A])(f: A => B): F[B]

trait MyMonad[F[_]] extends MyFunctor[F]:
    def flatMap[A, B](in: F[A])(f: A => F[B]): F[B]
    
object MyMonad:
    given MyMonad[Option] with
        def map[A, B](in: Option[A])(f: A => B): Option[B] = in.map(f)
        def flatMap[A,B](in: Option[A])(f: A => Option[B]): Option[B] = in.flatMap(f)

    // Type Lambda example: A.M.A.Z.I.N.G    
    given [E]: MyMonad[[X] =>> Either[E, X]] with 
        def map[A, B](in: Either[E, A])(f: A => B): Either[E, B] = in.map(f)
        def flatMap[A, B](in: Either[E, A])(f: A => Either[E,  B]) = in.flatMap(f)

    extension [F[_], A, B](value: F[A])(using F: MyFunctor[F]) def mapF(f: A => B): F[B] = F.map(value)(f)    
    extension [F[_], A, B](value: F[A])(using M: MyMonad[F]) def flatMapF(f: A => F[B]): F[B] = M.flatMap(value)(f)    