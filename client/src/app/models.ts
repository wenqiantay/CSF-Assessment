// You may use this file to create any models
export interface MenuItem {
    id: string
    name: string
    description: string
    price: number
    quantity: number
}

export interface Order {
    username: string
    password: string
    items: MenuItem[]
}

export interface Receipt {
    timestamp: string
    orderId: string
    paymentId: string
    total: number

}