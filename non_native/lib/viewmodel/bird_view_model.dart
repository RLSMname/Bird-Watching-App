import 'package:flutter/cupertino.dart';
import 'package:non_native/util/api_response.dart';

import '../model/bird.dart';
import '../model/habitat.dart';
import '../util/errors.dart';

//hybrid approach
class BirdViewModel with ChangeNotifier {
  ApiResponse _apiResponse = ApiResponse.initial();

  final List<Bird> _birds = [
    Bird(
        id: 1,
        name: 'Eagle',
        order: 'Accipitriformes',
        family: 'Accipitridae',
        habitat: Habitat.forest,
        sightCount: 5),
    Bird(
        id: 2,
        name: 'Sparrow',
        order: 'Passeriformes',
        family: 'Passeridae',
        habitat: Habitat.grassland,
        sightCount: 3),
    Bird(
        id: 3,
        name: 'Parrot',
        order: 'Psittaciformes',
        family: 'Psittacidae',
        habitat: Habitat.wetland,
        sightCount: 8),
    Bird(
        id: 4,
        name: 'Pigeon',
        order: 'Columbiformes',
        family: 'Columbidae',
        habitat: Habitat.forest,
        sightCount: 4),
  ];
  int _nextId = 10;

  ApiResponse get response {
    return _apiResponse;
  }

  Future<void> fetchAll() async {
    _apiResponse = ApiResponse.loading();
    notifyListeners();
    try {
      _apiResponse = ApiResponse.completed(_birds);
    } catch (e) {
      _apiResponse = ApiResponse.error(e.toString());
      print(e);
    }
    notifyListeners();
  }

  Future<void> deleteBird(int id) async {
    _apiResponse = ApiResponse.loading();
    notifyListeners();
    try {
      if (!_birds.any((bird) => bird.id == id)) {
        throw Exception(Errors.notFound.text);
      }
      _birds.removeWhere((bird) => bird.id == id);
      _apiResponse = ApiResponse.completed(_birds);
    } catch (e) {
      _apiResponse = ApiResponse.error(e.toString());
      print(e);
    }
    notifyListeners();
  }

  Future<void> fetchBirdById(int id) async {
    _apiResponse = ApiResponse.loading();
    notifyListeners();

    try {
      final Bird bird = _birds.firstWhere(
        (bird) => bird.id == id,
      );
      _apiResponse = ApiResponse.completed(bird);
    } catch (e) {
      _apiResponse = ApiResponse.error(Errors.notFound.text);
      print("Error fetching bird by id: $e");
    }
    notifyListeners();
  }
}
