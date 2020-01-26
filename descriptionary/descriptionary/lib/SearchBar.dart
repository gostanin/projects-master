import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:async';

class SearchBarState extends State<SearchBar>
{
  final myController = TextEditingController();

  @override
  Widget build(BuildContext context)
  {
    return Form(
      child: Column(
        children: <Widget>[
          Container(
            child: FutureBuilder<Word>(
              future: getWord(),
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Text(snapshot.data.lastName);
                }
                else {
                  return Text("error");
                }
              },
            ),
          ),
          Container(
            child: Row(
              children: < Widget > [
              Flexible(child: Padding(padding: EdgeInsets.only(left: 15),child: TextField(controller: myController,
              textAlign: TextAlign.center,
              decoration:
              InputDecoration(
              labelText: 'Give us the description of the word!',
              fillColor: Colors.grey,
              ),
              ),
              ),
              ),

              RaisedButton(
              color: Colors.black,
              onPressed: () {
              Scaffold.of(context).showSnackBar(SnackBar(content: Text(myController.text)));
              },
            child: Container(
          child: Text(('Find!'), style: TextStyle(color: Colors.white, fontSize: 20, fontStyle: FontStyle.italic)),//style: TextStyle(fontSize: 20),
        ),
      )

        ]
        ),
          )
        ],
      ),

    );

  }
  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    myController.dispose();
    super.dispose();
  }

  Future<Word> getWord() async {
    String url = 'https://reqres.in/api/users/2';
    final response =
    await http.get(url, headers: {"Accept": "application/json"});


    if (response.statusCode == 200) {
      return Word.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to load post');
    }
  }
}

class SearchBar extends StatefulWidget
{
  @override
  SearchBarState createState() => SearchBarState();
}

class Word
{
  final String firstName;
  final String lastName;

  Word({this.firstName, this.lastName});

  factory Word.fromJson(Map<String, dynamic> json)
  {
    return Word(
      firstName: json['data']['first_name'],
        lastName: json['data']['last_name']
    );
  }
}

