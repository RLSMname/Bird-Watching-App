import 'package:flutter/material.dart';
import 'package:non_native/util/routes.dart';
import 'package:non_native/view/components/header.dart';
import 'package:non_native/view/components/plus_button.dart';
import 'package:non_native/view/screens/add_edit_screen.dart';
import 'package:non_native/viewmodel/bird_view_model.dart';
import 'package:provider/provider.dart';

import '../../util/api_response.dart';
import '../components/bird_list_view.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({
    super.key,
  });

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  final String headerText = "Your collection";

  final String headerSubText = "ALWAYS READY FOR A NEW ADVENTURE";

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration.zero, () {
      Provider.of<BirdViewModel>(context, listen: false).fetchAll();
    });
  }

  Future<void> onDeleteBird(int id) async {
    print("DELETING WITH ID $id");
    try {
      await Provider.of<BirdViewModel>(context, listen: false).deleteBird(id);
      print("Bird with ID $id deleted successfully.");
    } catch (e) {
      print("Error deleting bird: $e");
    }
  }

  void onClickPlusButton() {
    print("CLICKED PLUS BUTTON");
    //navigate to add screen
    Navigator.pushNamed(context, RouteNames.add,
        arguments: AddEditScreenArguments(
            headerText: "Add a new bird",
            headerSubText: "GET A NEW FRIEND",
            birdId: null));
  }

  void onEditBird(int id) {
    print("EDITING WITH ID $id");
    Navigator.pushNamed(context, RouteNames.edit,
        arguments: AddEditScreenArguments(
            headerText: "Edit bird", headerSubText: "", birdId: id));
  }

  Widget mainWidget(BirdViewModel birdViewModel) {
    ApiResponse apiResponse = birdViewModel.response;
    switch (apiResponse.status) {
      case Status.loading:
        return const Center(child: CircularProgressIndicator());
      case null:
        return const Center(
          child: Text('Please try again latter!!!'),
        );
      case Status.completed:
        return Expanded(
          child: BirdListView(
            birds: apiResponse.data,
            isLoading: false,
            onDeleteBird: onDeleteBird,
            onEditBird: onEditBird,
          ),
        );

      case Status.error:
        return const Center(
          child: Text('Please try again latter!!!'),
        );
      case Status.initial:
        //birdViewModel.fetchAll();
        return const Center(child: CircularProgressIndicator());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Consumer<BirdViewModel>(builder: (context, birdViewModel, child) {
      return Scaffold(
        body: Column(
          children: [
            Header(text: headerText, subText: headerSubText),
            const SizedBox(
              height: 30,
            ),
            //plus button
            PlusButton(onClick: onClickPlusButton),
            //list
            const SizedBox(
              height: 20,
            ),

            mainWidget(birdViewModel)
          ],
        ),
      );
    });
  }
}
