# PexelsDemo
<div align=center><img width="300" height="600" src="https://github.com/DingCheng19/PexelsDemo/blob/master/screenshots/show.gif"/></div>

１、アプリケーションの内容について、簡単に説明させていただきます。
「https://www.pexels.com/api/documentation/#」というサイトのAPIを利用して、簡単な写真を検索するアプリです。
<div align=center><img width="300" height="600" src="https://github.com/DingCheng19/PexelsDemo/blob/master/screenshots/desktop.jpeg"/></div>
画面の真ん中はアプリのロゴです。
<div align=center><img width="300" height="600" src="https://github.com/DingCheng19/PexelsDemo/blob/master/screenshots/home1.jpeg"/></div>
これがアプリのメイン画面です。中には検索ボックスと検索ボタンと写真リストを表示するエリアがあります。
<div align=center><img width="300" height="600" src="https://github.com/DingCheng19/PexelsDemo/blob/master/screenshots/home2.jpeg"/></div>
キーワードが入力されていない場合は検索ボタンをクリックして、「キーワードを空栏にしてはいけません」というメッセージをポップアップします。
<div align=center><img width="300" height="600" src="https://github.com/DingCheng19/PexelsDemo/blob/master/screenshots/home4.jpeg"/></div>
キーワード「nature」を入力して検索ボタンをクリックして、「nature」に関する画像を表示します。写真リストは無限スクロールローディングに対応しています。写真の右下には、写真の作者の名前が表示されています。美観のために、名前の後ろにグラデーション色の半透明背景を追加し、円角処理を行いました。
<div align=center><img width="300" height="600" src="https://github.com/DingCheng19/PexelsDemo/blob/master/screenshots/detail1.jpeg"/></div>
リストの任意の写真をクリックすると、写真の詳細ページにジャンプします。写真を強調表示するために、背景色を黒に設定しました。
<div align=center><img width="300" height="600" src="https://github.com/DingCheng19/PexelsDemo/blob/master/screenshots/detail2.jpeg"/></div>
ユーザー体験を最適化するために、画像のズーム機能を追加しました。二本の指の操作によって、写真をスケーリングしたり、平行移動したりすることができます。


２、フレームと技術の説明
このアプリは、KotlinでMVVMのフレームワークを利用して、作成しました。
ネットワークは、KotlinのcoroutinesとRetrofitを利用して、実装しました。
UIテストは、ActivityScenarioを利用して、作成しました。

3、動画でアプリの機能を説明します
<div align=center><img width="300" height="600" src="https://github.com/DingCheng19/PexelsDemo/blob/master/screenshots/show.gif"/></div>
