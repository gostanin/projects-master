import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';
import 'dart:io';

class SettingsMenuState extends State<SettingsMenu>
{
  double _colorScheme = 1;
  SettingsConfig mainConfig;
  Settings _settings;

  SettingsMenuState();

  @override
  void initState() {
    super.initState();
    setState(() {
      _settings = Settings();
      mainConfig = _settings.getSettings();
    });
  }

  @override
  Widget build(BuildContext context)
  {
    return Scaffold(
      appBar: AppBar(
        title: Text("Settings", style: mainConfig.textStyle,),
        backgroundColor: mainConfig.appBarColor,
      ),
      body: _settingsBody(),
      backgroundColor: mainConfig.backgroundColor,
    );
  }

  Widget _settingsBody()
  {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        Container(
          child: RaisedButton(
            onPressed: () => _sendConfigBack(context),
            child: Text('BACK'),
          )
        ),
        Container(
          child: Slider(
            value: _colorScheme,
            activeColor: mainConfig.foregroundColor,
            inactiveColor: mainConfig.appBarColor,
            max: 4,
            min: 1,
            divisions: 3,
            onChanged: (newColor) {
              setState(() {
                _colorScheme = newColor;
                _setTheme();
              });
            },
          ),
        )
      ],
    );
  }



  void _sendConfigBack(BuildContext context)
  {
    _settings.setSettings(_colorScheme.toInt());
    _settings.writeCounter();
    Navigator.pop(context, _settings);
  }

  void _setTheme()
  {
    _settings.setSettings(_colorScheme.toInt());
    mainConfig = _settings.getSettings();
  }
}

class SettingsMenu extends StatefulWidget
{
  @override
  SettingsMenuState createState() => SettingsMenuState();
}

class SettingsConfig
{
  TextStyle textStyle;
  Color backgroundColor;
  Color appBarColor;
  Color foregroundColor;

  SettingsConfig(this.textStyle, this.backgroundColor, this.appBarColor, this.foregroundColor);
  SettingsConfig.emptyConstructor();
}

class Settings
{
  int _settings;
  SettingsConfig _light;
  SettingsConfig _dark;
  SettingsConfig _color1;
  SettingsConfig _color2;

  Settings()
  {
    initializeSettings();
  }

  void initializeSettings()
  {
    _light = SettingsConfig(TextStyle(color: Colors.black), Colors.white, Colors.grey, Color.fromARGB(255, 87, 87, 87));
    _dark = SettingsConfig(TextStyle(color: Colors.white), Color.fromARGB(255, 67, 69, 67), Colors.black, Colors.grey);
    _color1 = SettingsConfig(TextStyle(color: Colors.black), Colors.amber, Color.fromARGB(255, 2, 71, 181), Color.fromARGB(255, 2, 164, 176));
    _color2 = SettingsConfig(TextStyle(color: Colors.white), Color.fromARGB(255, 1, 95, 102), Colors.black, Colors.grey);

    readCounter().then((int value){
      _settings = value;
    });
  }

  void setSettings(int settings)
  {
    _settings = settings;
  }

  SettingsConfig getSettings()
  {
    if (_settings == 1)
      return _light;
    else if (_settings == 2)
      return _dark;
    else if (_settings == 3)
      return _color1;
    else
      return _color2;
  }

  Future<String> get _localPath async {
    final directory = await getApplicationDocumentsDirectory();

    return directory.path;
  }

  Future<File> get _localFile async {
    final path = await _localPath;

    return File('$path/counter.txt');
  }

  Future<File> writeCounter() async {
    final file = await _localFile;

    // Write the file.
    return file.writeAsString('$_settings');
  }

  Future<int> readCounter() async {
    try {
      final file = await _localFile;

      // Read the file.
      String contents = await file.readAsString();

      return int.parse(contents);
    } catch (e) {
      // If encountering an error, return 0.
      return 0;
    }
  }
}