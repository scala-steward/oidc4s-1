package me.wojnowski.oidc4s.json.circe

import me.wojnowski.oidc4s.PublicKeyProvider.JsonWebKey
import me.wojnowski.oidc4s.PublicKeyProvider.JsonWebKeySet
import me.wojnowski.oidc4s.PublicKeyProvider.KeyId

import io.circe.Decoder

trait JsonWebKeySetCirceDecoder {

  private implicit val jsonWebKeyCirceDecoder: Decoder[JsonWebKey] =
    Decoder.forProduct3[JsonWebKey, String, String, KeyId]("n", "e", "kid") { (n: String, e: String, kid: KeyId) =>
      JsonWebKey(n, e, kid)
    }

  protected implicit val jsonWebKeySetCirceDecoder: Decoder[JsonWebKeySet] =
    Decoder.forProduct1[JsonWebKeySet, List[JsonWebKey]]("keys")(JsonWebKeySet.apply)

}
