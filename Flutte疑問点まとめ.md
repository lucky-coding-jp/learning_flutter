#Flutter疑問点まとめ

---
###FlutterのWidget内のBodyに複数のChildを持たせたいときはどうすれば良いですか？

---Gemini---
Flutterでは、body に直接指定できるのは 1つの Widget だけです。

そのため、複数の子要素を表示したい場合は、Column、Row、ListView、Stack などの「複数の子を持てる Widget」でラップします。

例1: 縦に並べる (Column)
Scaffold(
  appBar: AppBar(
    title: const Text('Sample'),
  ),
  body: Column(
    children: [
      Text('Hello'),
      Text('World'),
      ElevatedButton(
        onPressed: () {},
        child: Text('Button'),
      ),
    ],
  ),
);
例2: 横に並べる (Row)
body: Row(
  children: [
    Icon(Icons.star),
    Text('Favorite'),
  ],
)
例3: スクロール可能な一覧 (ListView)

要素数が多い場合はこちらがよく使われます。

body: ListView(
  children: [
    Text('Item 1'),
    Text('Item 2'),
    Text('Item 3'),
  ],
)
よくある間違い

以下はコンパイルエラーになります。

body: [
  Text('Hello'),
  Text('World'),
]

body は Widget 型を要求しているのに、List<Widget> を渡しているためです。

Flutterでよく使うレイアウトWidget
Widget	用途
Column	縦方向に並べる
Row	横方向に並べる
ListView	スクロール可能な縦リスト
GridView	グリッド表示
Stack	重ねて表示
Wrap	自動改行しながら配置

最も基本的なのは Column(children: [...]) です。複数の Widget を body に置きたいときは、まず Column を使うことが多いです。



---
###Column型を指定したときに要素間の間を開けるにはどうすれば良いですか？
可能であれば、プラットフォームに依存しないようにデバイスの縦10%のように指定したいです。

---Gemini---
できます。

Flutterでは要素間の余白を作る方法はいくつかありますが、画面サイズに応じて変化させたいなら MediaQuery を使うのが一般的です。

方法1: SizedBox を挟む

例えば「画面の高さの10%」を空けるなら、

body: Column(
  children: [
    Text('Item 1'),

    SizedBox(
      height: MediaQuery.of(context).size.height * 0.1,
    ),

    Text('Item 2'),
  ],
)

MediaQuery.of(context).size.height

でデバイスの高さが取得できます。

例えば

iPhone SE (667px) → 約67px
iPhone 15 Pro Max (932px) → 約93px

のように自動調整されます。

方法2: Spacer を使う

上下の要素を比率で配置したい場合は Spacer が便利です。

body: Column(
  children: [
    Text('Top'),

    Spacer(),

    Text('Center'),

    Spacer(),

    Text('Bottom'),
  ],
)

これだと残りの空間を均等に分配します。

方法3: mainAxisAlignment を利用

要素数が少ないならこちらも簡単です。

body: Column(
  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
  children: [
    Text('A'),
    Text('B'),
    Text('C'),
  ],
)

選択肢としては

MainAxisAlignment.spaceBetween
MainAxisAlignment.spaceAround
MainAxisAlignment.spaceEvenly

があります。

方法4: Flutter 3.27以降なら spacing

最近のFlutterでは Column に spacing が追加されています。

Column(
  spacing: 20,
  children: [
    Text('A'),
    Text('B'),
    Text('C'),
  ],
)

ただし spacing は固定値なので、

spacing: MediaQuery.of(context).size.height * 0.02,

のように割合指定も可能です。

個人的によく使う形

画面サイズ依存の余白なら

final h = MediaQuery.of(context).size.height;

Column(
  children: [
    Text('A'),
    SizedBox(height: h * 0.05),
    Text('B'),
  ],
)

がシンプルです。

ただし、スマホとタブレットで見た目が大きく変わることもあるので、実務では「画面高さの10%」よりも Spacer や Expanded による比率レイアウト を使うことが多いです。例えば「上部20%、中央60%、下部20%」のような構成なら、端末ごとの差を吸収しやすくなります。