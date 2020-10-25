//data transfer type
enum class TransferType(val value:Int) {
    SharedPreferences(0),
    Bundle(1),
    Singleton(2);

    companion object {
        //convert  from Int to TransferType
        fun fromInt(value: Int) = TransferType.values().firstOrNull() { it.value == value }
    }
}