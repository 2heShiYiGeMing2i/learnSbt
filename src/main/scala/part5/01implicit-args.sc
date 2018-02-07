//简单的implicit 实例


def calcTax(amount: Float)(implicit rate: Float): Float = {
  amount * rate
}

object SimpleStateSalesTax {
  implicit val rate: Float = 0.05F
}

case class ComplicatedSalesTaxData(baseRate: Float, isTaxHoliday: Boolean, storeId: Int)

object complicatedSalesTax {
  private def extraTaxRateForStore(id: Int): Float = {
    0.0F
  }

  implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float = {
    if (cstd.isTaxHoliday) {
      0.0F
    } else {
      cstd.baseRate + extraTaxRateForStore(cstd.storeId)
    }
  }
}; {
  import SimpleStateSalesTax.rate
  val amount = 100F
  println(s"tax on $amount  = ${calcTax(amount)}")
}; {
  import complicatedSalesTax.rate
  implicit val myStore: ComplicatedSalesTaxData = ComplicatedSalesTaxData(0.06F, isTaxHoliday = false, 1010)
  val amount = 100F
  println(s"tax on $amount = ${calcTax(amount)}")
}; {
  import complicatedSalesTax.rate
  implicit val myStore: ComplicatedSalesTaxData = ComplicatedSalesTaxData(0.06F, isTaxHoliday = true, 1010)
  val amount = 100F
  println(s"tax on $amount = ${calcTax(amount)}")
}