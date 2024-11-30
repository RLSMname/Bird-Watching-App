import 'package:flutter/material.dart';
import 'package:non_native/model/bird.dart';
import 'package:non_native/model/habitat.dart';
import 'package:non_native/util/routes.dart';
import 'package:non_native/view/screens/add_edit_screen.dart';
import 'package:non_native/view/screens/main_screen.dart';
import 'package:non_native/viewmodel/bird_view_model.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  MyApp({super.key});

  final Bird myBird = Bird(
      id: 1,
      name: 'name',
      order: 'order',
      family: 'family',
      habitat: Habitat.wetland,
      sightCount: 10);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => BirdViewModel(),
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true,
        ),
        debugShowCheckedModeBanner: false,
        initialRoute: '/',
        routes: {
          RouteNames.index: (context) => const MainScreen(),
          RouteNames.add: (context) => const AddEditScreen(),
          RouteNames.edit: (context) => const AddEditScreen()
          //i just want them to have different route names
        },
      ),
    );
  }
}

/*

// home: MainScreen(
      //     onDeleteBird: (id) {}, onClickPlusButton: () {}, onEditBird: (id) {}),
      home: AddEditScreen(
        headerText: "placeholder",
        headerSubText: 'placeholder',
        // existingBird: myBird,
      ),
 */
