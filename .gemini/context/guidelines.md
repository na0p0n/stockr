# 実装ガイドライン

## Kotlin & Spring Boot
- コンストラクタ注入を推奨します（`@Autowired` は避ける）。
- Null安全を意識し、可能な限り `val` と非Null型を使用してください。

## MyBatis アノテーションの実装例
```kotlin
@Mapper
interface StockMapper {
    @Select("""
        SELECT id, name, quantity 
        FROM stocks 
        WHERE id = #{id}
    """)
    fun findById(id: String): StockDo?
}

- SQLキーワード（SELECT, FROM, WHERE等）は大文字で記述してください。
