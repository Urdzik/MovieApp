package movie.app.data.model.search


data class SearchResponse(
    val page: Int,
    val results: List<SearchItem>,
    val total_pages: Int,
    val total_results: Int
)
