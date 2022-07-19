import base64

hexString = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"

def hexToBase64(hex):
    hexAsBytes = bytearray.fromhex(hex)
    result = base64.b64encode(hexAsBytes)
    print(result)

hexToBase64(hexString)

# challenge 2
hexOne = "1c0111001f010100061a024b53535009181c"
hexTwo = "686974207468652062756c6c277320657965"

def hexToBytes(hex):
    return bytearray.fromhex(hex)

def bytesToBase64(bytes):
    return base64.b64encode(bytes)

def bytesToHex(bytes):
    return bytes.hex()

def xor(hex1, hex2):
    hex1decoded = hexToBytes(hex1)
    hex2decoded = hexToBytes(hex2)
    result = bytes(a ^ b for (a , b) in zip(hex1decoded, hex2decoded))
    return bytesToHex(result)

print(xor(hexOne, hexTwo))