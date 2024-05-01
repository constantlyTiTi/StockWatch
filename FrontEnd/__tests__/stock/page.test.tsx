import { render, screen } from "@testing-library/react";
import Page from "@/stock/page"
import {describe, expect, it, jest} from '@jest/globals'

jest.mock("@/components/stock/StockListTable",() => "StockList")


describe("Stock List Page ", () => {


    it("stock Home Page should render a stock List table", () => {
        const {container} = render(<Page/>);

        expect(container).toMatchSnapshot()

    })
})