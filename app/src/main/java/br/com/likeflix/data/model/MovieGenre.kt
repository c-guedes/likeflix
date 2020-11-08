package br.com.likeflix.data.model

enum class MovieGenre(val id: Int) {
    Action(28),
    Drama(18),
    Fantasy(14),
    ScienceFiction(878);

    companion object {
        fun getNameFromOrdinal(
            ordinal: Int
        ): String {
            return when (ordinal) {
                Action.ordinal -> "Ação"
                Drama.ordinal -> "Drama"
                Fantasy.ordinal -> "Fantasia"
                else -> "Ficção"
            }
        }
    }
}