package de.rlang.productconfigurator.scene.domain.model

data class Matrix4(val matrix: Array<Array<Float>>) {

    companion object {
        fun default(): Matrix4 = Matrix4(
            arrayOf(
                arrayOf(1.0f, 0.0f, 0.0f, 0.0f),
                arrayOf(0.0f, 1.0f, 0.0f, 0.0f),
                arrayOf(0.0f, 0.0f, 1.0f, 0.0f),
                arrayOf(0.0f, 0.0f, 0.0f, 1.0f),
            )
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix4

        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        return matrix.contentDeepHashCode()
    }
}