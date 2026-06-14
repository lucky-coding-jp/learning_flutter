class Mystate extends State<MyWidget> {
    AnimationController _controller;

    //インスタンスの初期化
    //初期化
    void initState(){
        _controller = AnimationController();
    }

    void setState((){
        //stateを更新する、これによりdidChangeDependencyが走り、子孫を再構築する
        //didUpdateWidget()が含まれるときは下のオブジェクトを全て読み出される
    })
    
    
    //依存関係が変更されたどうかを確認
    //Render
    void didChangeDependency(){
        //ウィジェットの背後にあるElementとリンクさせるもの
        //MediaQueryは、Flutterアプリケーションでデバイスの現在のメディア(アプリを含むウインドウ)のサイズや向きなどを取得するためのウィジェット
        size = calculations(MediaQuery.sizeOf(context));
    }

    //Render
    void didUpdateWidget(){

    }

    @override
    Widget build(BuildContext context){
        //use 'size'
    }
}

/*
ステートレス/ステートフルは描画を行わない
そのため、レンダリングオブジェクトを生成・更新するために使用するWidgets

abstract class RenderBox extends RenderObject{
  layout();
  paint(Canvas canvas);
  describeSemanticsConfiguration();
  bool hitTest(BoxHitTestResult result, {required Offset position});

  void layout(BoxConstraints constraints){
    innerConstraints = constraints.deflate(padding);
    child?.layout(innerConstraints);
    //child.size is now set!
  }

  void hitTest(HitTestResult result, Offset position){
    if(size.contains(position)) {
      result.add(this);
      hitTestChildren(result, position);
    }
  }

  abstract class RenderObject extends AbstractNode {
    void describeSemanticsConfiguration(SemanticsConfiguration config);
  }

  void mount(Element? parent) {
    newRenderObject = widget.createRenderObject();
    ancestorRenderObject.child = new RenderObject;
  }

  void performRebuild() {
    widget.updateRenderObject(this, renderObject);
  }

  class RenderString extends RenderBox {
    RenderString(this._value);

    String _value;
    String get value => _value;

    void set value(String newValue){
      if(newValue == _value) return;
      _value = newValue;
      markNeedsLayout();
      //文字は塗りつぶす設定等は不要なので使用しない
      //markNeedsPaint();
      markNeedsSemtanticsUpdate();
    }
  }
}
RendarObjectWidgets(){
}

//コードは /lib 内にdartコードを配置します。
*/