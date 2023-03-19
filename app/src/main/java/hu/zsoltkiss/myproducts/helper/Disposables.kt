package hu.zsoltkiss.myproducts.helper

import io.reactivex.rxjava3.disposables.CompositeDisposable

interface Disposables {
    val disposeBag: CompositeDisposable
}

/**
 * Common place for view models to create CompositeDisposable.
 * All view models should use it through delegation pattern so that each view model is guaranteed to have a dispose bag.
 */
class DisposeBag : Disposables {
    override val disposeBag = CompositeDisposable()
}