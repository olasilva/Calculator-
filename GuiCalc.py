from tkinter import *
import math

class calculator:
    def __init__(self, master):
        self.master = master
        self.master.title("silva's scientific calculator")
        self.master.configure(background='white')
        self.master.resizable(width=False, height=False)
        #self.master.geometry("480*568+450+90")

        self.total = 0
        self.current = ''
        self.input_value = True
        self.check_sum = False
        self.result = False

        self.txtDisplay = Entry(self.master, font=('arial',20, 'bold'), bd=30, insertwidth=4, width=14, borderwidth=4)
        self.txtDisplay.grid(row=0, column=0, columnspan=4)

        self.create_buttons()

    def create_buttons(self):
        numbers=[
            ('7',1,0),('8',1,1),('9',1,2),
            ('4',2,0),('5',2,1),('6',2,2),
            ('1',3,0),('2',3,1),('3',3,2),
            ('0',4,1)
        ]
        for(text, row,col)in numbers:
            Button(self.master, text=text, padx=20, pady=20,command=lambda num=text: self.numberEnter(num)).grid(row=row,  column=col)

        operations =[
            ('+','add'),('-','sub'),('*','multi'),('/','divide'),
            ('c', 'clear_entry'),('AC','all_clear_entry'),('=','sum of total')

        ]
        for (text, op) in operations:
            Button(self.master, text=text, padx=20, pady=20, command=lambda op=op: self.operation(operations)).grid(row=4, column=operations.index((text,op)))

        #scientific option button
            scientific =[
                ('sin',self.sin),('cos',self.cos),('tan',self.tan),
                #('log',self.log),('exp',self.exp),
                #('pi',self.Pi),
                #('e',self.e),
                ('sqrt',self.squared),
            ]
        for (text, func) in scientific: 
            Button(self.master, text=text, padx=20, pady=20, command=func).grid(row=5, column=scientific.index((text,func)))
    def numberEnter(self,num):
        self.result = False
        firstnum = self.txtDisplay.get()
        secondnum = str(num)
        if self.input_value:
            self.txtDisplay.delete(0,END)
            self.txtDisplay.insert(0, secondnum)
            self.input_value = False
        else:
            if secondnum == '.':
                if secondnum in firstnum:
                    return
                self.current = firstnum + secondnum
            self.display(self.current)

    def sum_of_total(self):
        self.current = float(self.current)
        self.result = True
        if self.check_sum:
            self.valid_function()
        else: 
            self.total = float(self.txtDisplay.get())

    def display(self, value):
        self.txtDisplay.delete(0,END)
        self.txtDisplay.insert(0, value)

    def valid_function(self):
        if self.op == "add":
            self.total += self.current
        elif self.op == "sub":
            self.total -=self.current
        elif self.op == "multi":
            self.total *= self.current
        elif self.op == "div":
            self.total /= self.current
        self.input_value = True
        self.check_sum =False
        self.display(self.total)
    
    def operation(self, operations):
        self.current = float(self.current)
        if self.check_sum:
            self.valid_function()
        elif not self.result:
            self.total = self.current
            self.input_value =True
        self.check_sum =True
        self.operations=operations
        self.result =False

    def clear_entry(self):
        self.result= False
        self.current = math.pi
        self.display(self.current)
    
    def squared(self):
        self.result =False
        self.current = math.sqrt(float(self.txtDisplay.get()))
        self.display(self.current)

    def sin(self):
        self.result = False
        self.current = math.sin(math.radians(float(self.txtDisplay.get())))
        self.display(self.current)

    def cos(self):
        self.result =False
        self.current = math.cos(math.radians(float(self.txtDisplay.get())))
    
    def tan(self):
        self.result = False
        self.current = math.cos(math.radians(float(self.txtDisplay.get())))
root = Tk()
calc = calculator(root)
root.mainloop()