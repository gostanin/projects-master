import 'package:descriptionary/SearchBar.dart';
import 'package:descriptionary/FancyInfo.dart';
import 'package:descriptionary/SettingsMenu.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget
{
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context)
  {
    return MaterialApp(
      title: 'Descriptionary', //correct title
      home: HomePage(),
    );
  }
}

class HomePageState extends State<HomePage>
{
  var _bodyType = 0;
  Widget searchBar = SearchBar();
  String dropdownValue = "Noun";
  String dropdownDifficultyValue = "Easy";
  Settings settings;
  SettingsConfig settingsConfig;

  HomePageState()
  {
    settings = Settings();
    settingsConfig = settings.getSettings();
  }

  @override
  Widget build(BuildContext context)
  {
      return Scaffold(
        appBar: AppBar(
          title: Text("Descriptionary!", style: settingsConfig.textStyle,),
          actions: <Widget>[
            IconButton(icon: Icon(Icons.settings), color: settingsConfig.backgroundColor, onPressed: _settingsGoTo)
          ],
          backgroundColor: settingsConfig.appBarColor,
        ),
          body: _getBody(),
        backgroundColor: settingsConfig.backgroundColor,
      );
  }

  void _settingsGoTo() async
  {
    settings = await Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => SettingsMenu(),
        ));
    settingsConfig = settings.getSettings();
  }


  Widget _getBody()
  {
    if (_bodyType == 1)
      return _suggestionsBody();
    else if (_bodyType == 2)
      return _fancyInfo();
    else if (_bodyType == 3)
      return _gameBody();
    else
      return _mainBody();
  }

  Widget _fancyInfo()
  {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        Container(
          child: searchBar,
        ),
        Container(
          child: Column(
            children: <Widget>[
              Container(
                child: Text("The Word of the Day is: Incomplete!"),
              ),
              Container(
                child: Text("The definition is Unavailable!"),
              ),
              Container(
                child: Text("This app is INCOMPLETE"),
              )
            ]
          ),//Text("The Word of the Day is: Incomplete!"),
        ),
        Container(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Container(
                child: _testWidget('Home', 0),
              ),
              Container(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Container(
                      child: _testWidget('Suggestions', 1)
                    ),
                    Container(
                      child: _testWidget('Game', 2)
                    )
                  ],
                ),
              ),
            ],
          ),
        ),


      ]
    );
  }

  
  Widget _suggestionsBody()
  {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        Container(
          child: searchBar,
        ),
        Container(
          child: _testWidget('Suggestion1',1),//change body type
        ),
        Container(
          child: _testWidget('Suggestion2',1),//change body type
        ),
        Container(
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                child: _testWidget('Home',0),
              )
            ],
          ),
        )
      ],
    );
  }

  Widget _mainBody()
  {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        Container(
          child: searchBar,
        ),
        Container(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Container(
                  child: _testWidget('Suggestions',1),

              ),
              Container(
                child: _testWidget('FancyInfo',2),
              )
            ],
          ),
        ),
        Container(
          child: _testWidget('Game',3),
        )
      ],
    );
  }

  void _setBody(int val)
  {
     setState(() {
       _bodyType = val;
     });
  }

  Widget _testWidget(String name, int bodyType)
  {
    return RaisedButton(
      color: settingsConfig.foregroundColor,
        onPressed: () {
          _setBody(bodyType);
        },
        child: Container(
          child: Text(name, style: settingsConfig.textStyle,),
        ),
    );
  }
  Widget _gameBody()
  {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        Container(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Container(
                child: Column(
                  children: <Widget>[
                    Container(
                      child: Text('Type of Word!'),
                    ),
                    Container(
                      child: DropdownButton<String>(
                        value: dropdownValue,
                        icon: Icon(Icons.arrow_downward),
                        iconSize: 24,
                        elevation: 16,
                        style: settingsConfig.textStyle,
                        underline: Container(
                          height: 2,
                          color: settingsConfig.foregroundColor,
                        ),
                        onChanged: (String newValue) {
                          setState(() {
                            dropdownValue = newValue;
                          });
                        },
                        items: <String>['Noun', 'Verb', 'Adjective']
                            .map<DropdownMenuItem<String>>((String value) {
                          return DropdownMenuItem<String>(
                            value: value,
                            child: Text(value),
                          );
                        }).toList(),
                      ),
                    )
                  ],
                ),
              ),
              Container(
                child: Column(
                  children: <Widget>[
                    Container //Second row of column
                      (
                      child: Text("Difficulty!"),
                    ),
                    Container(
                      child: DropdownButton<String>(
                        value: dropdownDifficultyValue,
                        icon: Icon(Icons.arrow_downward),
                        iconSize: 24,
                        elevation: 16,
                        style: settingsConfig.textStyle,
                        underline: Container(
                          height: 2,
                          color: settingsConfig.foregroundColor,
                        ),
                        onChanged: (String newValue) {
                          setState(() {
                            dropdownDifficultyValue = newValue;
                          });
                        },
                        items: <String>['Easy', 'Medium', 'Hard']
                            .map<DropdownMenuItem<String>>((String value) {
                          return DropdownMenuItem<String>(
                            value: value,
                            child: Text(value),
                          );
                        }).toList(),
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
        Container(
          child: RaisedButton(
            color: settingsConfig.foregroundColor,
            onPressed: (){Scaffold.of(context).showSnackBar(SnackBar(content: Text("This would normally move to the Game route.")));},
            child: Text('Start', style: settingsConfig.textStyle,),
          ),
        ),
        Container(
            child: RaisedButton(
              color: settingsConfig.foregroundColor,
              onPressed: (){Scaffold.of(context).showSnackBar(SnackBar(content: Text("This will rearrange to the Leaderboard Menu")));},
              child: Text ("Leaderboard", style: settingsConfig.textStyle,),
            )
        ),
        Container(
            child: _testWidget("Home", 0)
        )
      ],
    );
  }
}

class HomePage extends StatefulWidget
{
  @override
  HomePageState createState() => HomePageState();

}
