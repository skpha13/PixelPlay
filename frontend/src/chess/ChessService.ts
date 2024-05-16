import axios from "axios";

export class ChessService {
    public static async createGame(): Promise<string>{
        try {
            const response = await axios.get('http://localhost:8080/chess')
            console.log(response.data)
            return response.data
        } catch (e) {
            return ""
        }
    }
}