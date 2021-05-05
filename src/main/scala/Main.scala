import fp.MyMonad.*

@main def app: Unit = {
    val none = Option(1).flatMapF(_ => None)
}

