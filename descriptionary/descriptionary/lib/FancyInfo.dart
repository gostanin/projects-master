import 'package:flutter/material.dart';

class FancyInfoState extends State<FancyInfo>
{
  @override
  Widget build(BuildContext context)
  {
    return RaisedButton(
      color: Colors.black,
      onPressed: () {

      },
      child: Container(
        child: Text('FancyInfo', style: TextStyle(color: Colors.white, fontSize: 20, fontStyle: FontStyle.italic),),
      ),
    );
  }
}

class FancyInfo extends StatefulWidget
{
  @override
  FancyInfoState createState() => FancyInfoState();
}