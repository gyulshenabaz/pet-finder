
package com.petfinder.util

import com.petfinder.api.animal.model.Animal
import com.petfinder.api.animal.response.AnimalResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true
}

val previewAnimal: Animal
    get() {
        return json.decodeFromString<AnimalResponse>(
            string = """
{
    "animal": {
        "id": 52688791,
        "organization_id": "VA583",
        "url": "https://www.petfinder.com/dog/rogers-52688791/va/midlothian/sanctuary-rescue-va583/?referrer_id=7be8c2c8-e1a0-4a6c-b728-a7742e45ed29",
        "type": "Dog",
        "species": "Dog",
        "breeds": {
            "primary": "Beagle",
            "secondary": "Retriever",
            "mixed": true,
            "unknown": false
        },
        "colors": {
            "primary": "Tricolor (Brown, Black, & White)",
            "secondary": "Sable",
            "tertiary": null
        },
        "age": "Baby",
        "gender": "Male",
        "size": "Medium",
        "coat": null,
        "attributes": {
            "spayed_neutered": true,
            "house_trained": false,
            "declawed": null,
            "special_needs": false,
            "shots_current": true
        },
        "environment": {
            "children": true,
            "dogs": true,
            "cats": true
        },
        "tags": [
            "Affectionate",
            "Friendly",
            "Curious",
            "Loves kisses"
        ],
        "name": "Rogers",
        "description": "Hello! Thank you for checking out Rogers’  adoption listing. Here are a few key points on our adoption process. Please...",
        "organization_animal_id": null,
        "photos": [
            {
                "small": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/1/?bust=1629197434&width=100",
                "medium": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/1/?bust=1629197434&width=300",
                "large": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/1/?bust=1629197434&width=600",
                "full": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/1/?bust=1629197434"
            },
            {
                "small": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/2/?bust=1629197435&width=100",
                "medium": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/2/?bust=1629197435&width=300",
                "large": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/2/?bust=1629197435&width=600",
                "full": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/2/?bust=1629197435"
            },
            {
                "small": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/3/?bust=1629197436&width=100",
                "medium": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/3/?bust=1629197436&width=300",
                "large": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/3/?bust=1629197436&width=600",
                "full": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/3/?bust=1629197436"
            },
            {
                "small": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/4/?bust=1629197436&width=100",
                "medium": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/4/?bust=1629197436&width=300",
                "large": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/4/?bust=1629197436&width=600",
                "full": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/4/?bust=1629197436"
            },
            {
                "small": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/5/?bust=1629197437&width=100",
                "medium": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/5/?bust=1629197437&width=300",
                "large": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/5/?bust=1629197437&width=600",
                "full": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/5/?bust=1629197437"
            },
            {
                "small": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/6/?bust=1629197438&width=100",
                "medium": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/6/?bust=1629197438&width=300",
                "large": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/6/?bust=1629197438&width=600",
                "full": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/6/?bust=1629197438"
            }
        ],
        "primary_photo_cropped": {
            "small": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/1/?bust=1629197434&width=300",
            "medium": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/1/?bust=1629197434&width=450",
            "large": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/1/?bust=1629197434&width=600",
            "full": "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/52688791/1/?bust=1629197434"
        },
        "videos": [],
        "status": "adoptable",
        "status_changed_at": "2021-08-17T10:50:39+0000",
        "published_at": "2021-08-17T10:50:39+0000",
        "distance": null,
        "contact": {
            "email": "sanctuaryrescuepetfinder@gmail.com",
            "phone": null,
            "address": {
                "address1": null,
                "address2": null,
                "city": "Midlothian",
                "state": "VA",
                "postcode": "23112",
                "country": "US"
            }
        },
        "_links": {
            "self": {
                "href": "/v2/animals/52688791"
            },
            "type": {
                "href": "/v2/types/dog"
            },
            "organization": {
                "href": "/v2/organizations/va583"
            }
        }
    }
}
            """.trimIndent()
        ).animal
    }
