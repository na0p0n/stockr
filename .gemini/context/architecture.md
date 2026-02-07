# プロジェクト・アーキテクチャ定義

このプロジェクトは、**オニオンアーキテクチャ**を採用しています。

## 1. 依存関係のルール
- 依存の方向は常に **外側から内側** です。
- `domain` 層は、他のいかなる層（`infra`, `application`, `present`）にも依存してはいけません。

## 2. 各パッケージの役割

### domain (内側)
- **entity**: ビジネスロジックを保持する純粋なKotlinクラス。
- **repository**: データアクセス用のインターフェース。実装（MyBatis等）に依存しない抽象的な定義のみを置く。

### application
- **usecase**: `domain` を組み合わせて業務シナリオを実現する。
- **dto**: 外部とのデータ受け渡し専用オブジェクト。

### infra (外側)
- **mapper**: MyBatisの `@Mapper` インターフェース。
    - **重要**: SQLは `@Select(""" SQL """)` のようにアノテーションで記述すること。
- **repositoryImpl**: `domain/repository` の実装クラス。
    - `mapper` を呼び出し、DBのDTO（Data Object）と `domain/entity` の相互変換を行う。

### present (外側)
- **controller**: HTTPエンドポイント。
- **presenter**: クライアントへのレスポンス整形。

## 3. レビュー時の重点チェック項目
- `domain` パッケージ内に `org.apache.ibatis.annotations` がインポートされていないか？（インフラ依存の禁止）
- UseCaseから直接 `Mapper` を呼び出していないか？（必ず `Repository` を介すること）
- 新しいテーブル操作を追加する場合、必ず `infra/mapper` と `infra/repositoryImpl` の両方を更新しているか？