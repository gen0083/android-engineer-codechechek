# 株式会社ゆめみ Android エンジニアコードチェック課題

## 概要

本プロジェクトは株式会社ゆめみ（以下弊社）が、弊社に Android エンジニアを希望する方に出す課題のベースプロジェクトです。

そのベースプロジェクトを利用してKotlin2.0、KMPの学習に利用しているのが本プロジェクトです。

## アプリ仕様

本アプリは GitHub のリポジトリを検索するアプリです。

<!-- TODO: ある程度できあがったら画像差し替え -->

<img src="docs/app.gif" width="320">

（開発中のため実際の画面とは異なります、最新版は後述のWeb版を確認してください）

### 環境

- IDE：Android Studio Jellyfish | 2023.3.1 Patch 1
- Kotlin：2.0
- Compose Multiplatform: 1.6.11
- Java：17
- Gradle：8.7
- Android Gradle Plugin: 8.4.1
- minSdk：23
- targetSdk：34

### アプリの構成

- DI: Koin
- Navigation: Voyager
- UI: Jetpack Compose / Compose Multiplatform
- Network: Ktor
- Json: Kotlinx-serialization
- Images: Coil3

### 動作

KMP構成のためWebで動作確認可能です（<https://gen0083.github.io/android-engineer-codechechek/>）

1. 何かしらのキーワードを入力
2. GitHub API（`search/repositories`）でリポジトリを検索し、結果一覧を概要（リポジトリ名）で表示
3. 特定の結果を選択したら、該当リポジトリの詳細（リポジトリ名、オーナーアイコン、プロジェクト言語、Star 数、Watcher 数、Fork 数、Issue 数）を表示

## 参考記事

提出された課題の評価ポイントに関しては、[こちらの記事](https://qiita.com/blendthink/items/aa70b8b3106fb4e3555f)に詳しく書かれてありますので、ぜひご覧ください。
