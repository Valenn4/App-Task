package com.example.app_task.mvp.presenter

import com.example.app_task.mvp.contract.MainContract
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

internal class MainPresenterTest {

    private lateinit var presenter: MainContract.Presenter

    @RelaxedMockK
    private lateinit var view : MainContract.View

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = MainPresenter(view)
    }

    @Test
    fun `iniziation true`() {
        every { view.conditionSharedPreferences() } returns true
        presenter.inization()
    }

    @Test
    fun `iniziation false`() {
        every { view.conditionSharedPreferences() } returns false
        presenter.inization()
        verify { view.loadRecycler() }
        verify { view.invisibleText() }
    }

    @Test
    fun setOnClickAddButton() {
        every { view.setOnClickAddButton { any() } } just Runs
        presenter.setOnClickAddButton()
        verify { view.invisibleText() }
        verify { view.loadRecycler() }
        verify { view.addNewTask() }
    }

    @Test
    fun onClickItemRecycler() {
        every { view.conditionSharedPreferences() } returns true
        every { view.onClickItemRecycler(1) } just Runs
        presenter.onClickItemRecycler(1)
        verify { view.loadRecycler() }
        verify { view.visibleText() }
    }
}